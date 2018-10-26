# Testaus

| Kuvan koko    | Mergesort | Selsort | 1D Gauss (kernel 31) | 2D Gauss (kernel 31*31) |
|:------------- | ---------:| -------:| --------:| --------:|
| 200*200       | 25ms      | 23ms    | 40ms     | 330ms    |
| 287*303       | 42ms      | 35ms    | 64ms     | 717ms    |
| 287*380       | 39ms      | 39ms    | 81ms     | 897ms    |
| 720*1280      | 165ms     | 225ms   | 513ms    | 7600ms   |
| 960*953       | 178ms     | 282ms   | 526ms    | 7724ms   |
| 1920*1200     | 347ms     | 947ms   | 1292ms   | 18840ms  |

## Pixelsort
Mergesort on huomattavasti nopeampi kuin selectionsort suurilla kuvilla kuten olettaisikin (sillä mergesortin aikavaativuus on O(nlogn) ja selectionsort on O(n^2)).

Kiinnostavaa on että selectionsort on hieman nopeampi hyvin pienillä kuvilla. Tämä johtuu varmaankin mergesortin rekursiivisista kutsuista joissa menee suhteessa muuhun laskentaan enemmän aikaa pienillä listoilla.

## GaussBlur
1D on paljon nopeampi kuin 2D gaussblur. O(nm) (missä n on kernelin leveys ja m on kuvan koko) on 1D algoritmin aikavaativuus. 2D algoritmille tämä on O(n^2m) sillä kerneli on kaksiulotteinen.
