set term png
set output "balance.png"
plot 'balance.data' using 1:2 title "Tuna/shark population" with lines
