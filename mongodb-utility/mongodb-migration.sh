#!/bin/bash

# 默认配置文件路径
CONFIG_FILE="./db_config.cfg"

# 解析命令行参数
while getopts "t:c:" opt; do
  case $opt in
    t) TARGET_ENV="$OPTARG" ;;
    c) COLLECTION_NAME="$OPTARG" ;;
    *) echo "用法: $0 -t [test|dev] -c collection_name"; echo "  -t test: 生产迁移至测试环境"; echo "  -t dev: 生产迁移至开发环境"; echo "
    -c collection_name: 指定要迁移的集合名称"; exit 1 ;;
  esac
done

# 验证必要参数
if [[ -z "$TARGET_ENV" || -z "$COLLECTION_NAME" ]]; then
  echo "Missing required parameters"
  echo "Usage: $0 -t [test|dev] -c collection_name"
  exit 1
fi

# 加载配置文件
if [[ ! -f "$CONFIG_FILE" ]]; then
  echo "Config file $CONFIG_FILE not found!"
  exit 1
fi
source "$CONFIG_FILE"

# 设置目标数据库URI
case "$TARGET_ENV" in
  test) TARGET_URI="$TEST_DB_CONNECTION" ;;
  dev) TARGET_URI="$DEV_DB_CONNECTION" ;;
  *) echo "Invalid target: $TARGET_ENV. Must be 'test' or 'dev'"; exit 1 ;;
esac

SOURCE_URI="$PROD_DB_CONNECTION"

# 从URI中提取数据库名
SOURCE_DB=$(echo "$SOURCE_URI" | awk -F'/' '{print $NF}' | cut -d '?' -f1)
TARGET_DB=$(echo "$TARGET_URI" | awk -F'/' '{print $NF}' | cut -d '?' -f1)

# 检查源集合是否存在
EXIST_QUERY="db.getCollectionNames().includes('$COLLECTION_NAME')"
COLLECTION_EXISTS=$(mongosh "$SOURCE_URI" --quiet --eval "$EXIST_QUERY")

if [[ "$COLLECTION_EXISTS" != "true" ]]; then
  echo "ERROR: Collection '$COLLECTION_NAME' does not exist in source database"
  exit 1
fi

# 获取集合大小（MB）
SIZE_QUERY="db.getCollection('$COLLECTION_NAME').stats().size / (1024 * 1024)"
COLLECTION_SIZE=$(mongosh "$SOURCE_URI" --quiet --eval "$SIZE_QUERY")


# 显示迁移信息
echo "----------------------------------------"
echo "Source DB: $SOURCE_URI"
echo "Target DB: $TARGET_URI"
echo "Collection: $COLLECTION_NAME"
echo "Collection size: $COLLECTION_SIZE MB"
echo "----------------------------------------"
echo "WARNING: All data in TARGET  $COLLECTION_NAME will be COVERED!"
echo "----------------------------------------"

# 用户确认
read -p "Do you want to continue? (y/n): " CONFIRM
if [[ "$CONFIRM" != "y" && "$CONFIRM" != "Y" ]]; then
  echo "Migration cancelled"
  exit 0
fi

# 创建临时目录
TMP_DIR=$(mktemp -d)
echo "Created temp directory: $TMP_DIR"

# 执行mongodump
echo "Exporting collection from production..."
mongodump --uri="$SOURCE_URI" --collection="$COLLECTION_NAME" --out="$TMP_DIR"

if [ $? -ne 0 ]; then
  echo "mongodump failed!"
  rm -rf "$TMP_DIR"
  exit 1
fi

# 执行mongorestore
echo "Importing collection to target database..."
mongorestore --uri="$TARGET_URI" --collection="$COLLECTION_NAME" --drop \
  "$TMP_DIR/$SOURCE_DB/$COLLECTION_NAME.bson"

if [ $? -ne 0 ]; then
  echo "mongorestore failed!"
  rm -rf "$TMP_DIR"
  exit 1
fi

# 清理临时文件
rm -rf "$TMP_DIR"
echo "Migration completed successfully!"