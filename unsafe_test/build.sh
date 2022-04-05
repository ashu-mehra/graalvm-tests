#!/bin/bash

if [[ -z $JAVA_HOME ]]; then
	echo "JAVA_HOME is not set. Please set it to GraalVM installation directory."
	exit 1;
fi

echo "JAVA_HOME: ${JAVA_HOME}"
export PATH="$JAVA_HOME/bin:$PATH"

javac UnsafeTest.java
cat > manifest.mf << EOF
Manifest-Version: 1.0
Main-Class: UnsafeTest
EOF

jar cmf manifest.mf test.jar UnsafeTest.class UnsafeTest.java Data.class
native-image --initialize-at-build-time=UnsafeTest,Data -jar test.jar test

javac -cp .:${JAVA_HOME}/lib/svm/builder/svm.jar Data_Util.java
jar cmf manifest.mf test-recompute.jar UnsafeTest.class UnsafeTest.java Data.class Data_Util.java Data_Util.class
native-image --initialize-at-build-time=UnsafeTest,Data,Data_Util -jar test-recompute.jar test-recompute


