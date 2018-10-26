# imgfx
A program for manipulating images with a variety of filters or effects.

# Usage instructions
Download the latest [release](https://github.com/nicohi/imgfx/releases/tag/1.0) or build from source.
## Building from source
Requires gradle and java.
```
$ git clone https://github.com/nicohi/imgfx
$ cd imgfx/imgfx
$ gradle build
```
## Running program
New image is written to out.png in your current path.

To view help:
```
$ java -jar imgfx.jar
```
To run benchmark:
```
$ java -jar imgfx.jar bench <path to picture>
```

To run 1D gaussian blur:
```
$ java -jar imgfx.jar gblur1d <path to picture> <a number (1-50)>
```
To run 2D gaussian blur:
```
$ java -jar imgfx.jar gblur2d <path to picture> <a number (1-50)>
```
To run pixelsort:
```
$ java -jar imgfx.jar psort <path to picture> <a number (0-255)>

```
To run edgedetect:
```
$ java -jar imgfx.jar edge <path to picture>

```
# Documentation

[maarittely](dokumentaatio/maarittely.md)

[testaus](dokumentaatio/testaus.md)

[toteutus](dokumentaatio/toteutus.md)


# Weekly reports
[Viikkoraportti1](dokumentaatio/Viikkoraportti1.md)

[Viikkoraportti2](dokumentaatio/Viikkoraportti2.md)

[Viikkoraportti3](dokumentaatio/Viikkoraportti3.md)

[Viikkoraportti4](dokumentaatio/Viikkoraportti4.md)

[Viikkoraportti5](dokumentaatio/Viikkoraportti5.md)

[Viikkoraportti6](dokumentaatio/Viikkoraportti6.md)
