#!/bin/sh

cd "`dirname "$0"`"

javac *.java

java starter

echo 'Press enter to close'
read

