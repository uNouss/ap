#!/bin/bash
cp ressources/* classes/ 2>/dev/null
cd classes
export CLASSPATH=`find ../lib -name "*.jar" | tr '\n' ':'`
java -cp ${CLASSPATH}:. $@
cd ..

