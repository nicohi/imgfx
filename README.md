# imgfx
A program for manipulating images with a variety of filters or effects.
[testaus](dokumentaatio/testaus.md)

# Usage instructions
Requires gradle and java.
```
$ git clone https://github.com/nicohi/imgfx
$ cd imgfx/imgfx
$ gradle build
```
To run benchmark:
```
$ java -jar build/libs/imgfx.jar bench <path to picture>
```

To run gaussian blur:
```
$ java -jar build/libs/imgfx.jar gblur <path to picture> <a number (1-50)>
```

To run pixelsort:
```
$ java -jar build/libs/imgfx.jar psort <path to picture> <a number (0-255)>
```

# Weekly reports
[Viikkoraportti1](dokumentaatio/Viikkoraportti1.md)

[Viikkoraportti2](dokumentaatio/Viikkoraportti2.md)

[Viikkoraportti3](dokumentaatio/Viikkoraportti3.md)

[Viikkoraportti4](dokumentaatio/Viikkoraportti4.md)

[Viikkoraportti5](dokumentaatio/Viikkoraportti5.md)

[Viikkoraportti6](dokumentaatio/Viikkoraportti6.md)
