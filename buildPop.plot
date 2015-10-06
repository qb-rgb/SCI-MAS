set term png
set output "pop.png"
plot 'pop.data' using 1:2 title "tuna" with lines, 'pop.data' using 1:3 title "shark" with line
