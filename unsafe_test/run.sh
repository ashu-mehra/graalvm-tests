#!/bin/bash

echo "Running UnsafeTest without recomputation"
./test

echo "Running UnsafeTest with recomputation"
./test-recompute

