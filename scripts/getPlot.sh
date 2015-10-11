#!/bin/sh

echo "Building plots..."
gnuplot scripts/buildBalance.plot
gnuplot scripts/buildPop.plot
echo "All the plots are in the watordata directory."
