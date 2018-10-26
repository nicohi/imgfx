# Toteutus
Ohjelman eri filtterit ovat paketoitu .imgfx.filters pakettiin. Filterien yhteisessä käytössä olevat metodit ovat .imgfx.util paketissa. Benchmark toiminto sekä käyttöliittymä ovat myös omissa paketeissaan.

## Gaussian Blur
GaussBlur luokka generoi halutun kokoisen kernelin käyttäen gaussinkäyrän kaavaa. Luokassa on metodi 1D ja 2D kernelille. Luokka kutsuu sitten Kernel luokan applyKernel() metodia.
## Edge detect
EdgeDetect luokka sisältää muutamaan valmiin reunantunnistukseen käytettävän kernelin. Se käyttää Kernel luokan applyKernel2D() metodia sekä GaussBlur luokkaa.
## Kernel
Luokka sisältää applyKernel() metodin 1D ja 2D kerneleille. Metodi käy jokaiselle kuvan pixelille kernelin kokeisen alueen läpi ja laskee niiden yhteisen arvon. Tämä arvo säilötään uuteen taulukkoon jonka metodi sitten palauttaa.
## Pixel sort
Luokka sisältää selectionsort sekä mergesortin pixeleille. Jotta efekti olisi kiinnostavamman näköinen, luokka pystyy järjestämään vain alueet jotka ovat yli tietyn kirkkauden.
## Picture
Luokka mahdollistaa kuvatiedostojen muuntamisen javan int taulukoksi. Luokassa on myös yleisiä kuvien metodeja (kuten grayscale() ja rotate()).

# Yksikkötestaus
![kattavuus](kattavuus.png?raw=true)
Util pakkauksen kuvan import ja export toiminnot on testattu manuaalisesti. Benchmark toimintoa ei mielestäni tarvinnut testata, sillä se käyttää valmiiksi testattuja metodeja eikä tee mitään erikoista.
