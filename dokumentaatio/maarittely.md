# Määrittely
Ohjelma käsittelee kuvia eri tavoilla (Gaussian blur, pixel sort, ja mahdollisesti muita).
Käyttöliittymä antaa käyttäjän määritellä efektien parametrit. Huolena ovat suurikokoiset tiedostot.

## Algoritmit
- tehokas 1D Gaussian blur algoritmi (http://rastergrid.com/blog/2010/09/efficient-gaussian-blur-with-linear-sampling/) 
- hitaampi 2D gaussian blur
- edgedetect hyödyntäen samoja kernel-metodeja kuin gaussblur
- Merge- ja Quicksort kuvien väriarvoille

## Tietorakenteet
- taulukko kuvien pixelien säilömiseen (kuva on periaatteessa jo taulukko väriarvoja)

Valitsin kyseiset tietorakenteet koska ne ovat mahdollisimman tehokkaat tavat säilöä paljon dataa (kuvat ovat usein monia megatavuja).

## Aika- ja tilavaativuudet
Gaussian blur algoritmille toivottu aikavaativuus olisi O(n) ja tilavaativuus olisi myös O(n).
Pixel sort efektin aika- ja tilavaativuus riippuu järjestysalgoritmista. Aion kokeilla ainakin Quicksort ja Mergesort algoritmeja ja verrata niiden nopeuksia.


# Lähteet
https://computergraphics.stackexchange.com/questions/39/how-is-gaussian-blur-implemented

https://en.wikipedia.org/wiki/Gaussian_blur

http://datamoshing.com/2016/06/16/how-to-glitch-images-using-pixel-sorting/

http://rastergrid.com/blog/2010/09/efficient-gaussian-blur-with-linear-sampling/
