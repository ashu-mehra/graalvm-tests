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

rm -f test.jar
jar cmf manifest.mf test.jar UnsafeTest.class UnsafeTest.java
# Create native image without the substitution class that recomputes the field
native-image --initialize-at-build-time=UnsafeTest -jar test.jar test

# UnsafeTest_Util is the substitution class that recomputes the field containing field offset
javac -cp .:${JAVA_HOME}/lib/svm/builder/svm.jar UnsafeTest_Util.java
rm -f test-recompute.jar
jar cmf manifest.mf test-recompute.jar UnsafeTest.class UnsafeTest.java UnsafeTest_Util.java UnsafeTest_Util.class
native-image --initialize-at-build-time=UnsafeTest,UnsafeTest_Util -jar test-recompute.jar test-recompute


