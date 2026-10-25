#!/bin/bash

# XVelocity Build Script
VERSION="26.10.25.1"

echo "Building XVelocity v${VERSION}..."

# 检查 Maven
if ! command -v mvn &> /dev/null; then
    echo "Error: Maven is not installed"
    exit 1
fi

# 清理并编译
mvn clean package -q

if [ $? -eq 0 ]; then
    # 复制构建产物到输出目录
    mkdir -p build
    cp target/XVelocity-${VERSION}.jar build/
    echo "Build successful! Output: build/XVelocity-${VERSION}.jar"
else
    echo "Build failed!"
    exit 1
fi
