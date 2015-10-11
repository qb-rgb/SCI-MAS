set term png
set output "watordata/pop.png"
plot 'watordata/pop.data' using 1:2 title "tuna" with lines, 'watordata/pop.data' using 1:3 title "shark" with line
