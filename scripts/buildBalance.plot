set term png
set output "watordata/balance.png"
plot 'watordata/balance.data' using 1:2 title "Tuna/shark population" with lines
