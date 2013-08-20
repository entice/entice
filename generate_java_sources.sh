#!/bin/sh

##
# For copyright information see the LICENSE document.
##

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

SRC_DIR="$DIR/protos"
DST_DIR="$DIR/target/java"

# used for protoc to find and link the sources correctly
PCKG="entice/protocol"


# clear the old dir and recreate it
rm -rf "$DST_DIR"
mkdir -p "$DST_DIR"


# finally generate the java files (we need to cd to protos because)
# the dir where protoc is started needs to equal the package root dir
cd "$SRC_DIR"

# generate util protos
protoc -I="./" --java_out="$DST_DIR" "$PCKG/util.proto"
protoc -I="./" --java_out="$DST_DIR" "$PCKG/entity_system.proto"

# generate process and service protos
protoc -I="./" --java_out="$DST_DIR" "$PCKG/login_service.proto"
protoc -I="./" --java_out="$DST_DIR" "$PCKG/world_update.proto"