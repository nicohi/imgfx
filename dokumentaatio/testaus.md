TODO
### Benchmark dataa
```
$ java -jar imgfx/build/libs/imgfx.jar bench tst2.jpg 

Image size is 2038x4512

Testing mergesort

        time taken: 1233ms

Testing selectionsort

        time taken: 4366ms

```
```
$ java -jar imgfx/build/libs/imgfx.jar bench tst.jpg 

Image size is 960x953

Testing mergesort

        time taken: 183ms

Testing selectionsort

        time taken: 280ms
```
```
$ java -jar imgfx/build/libs/imgfx.jar bench s.jpg 

Image size is 1920x1200

Testing mergesort

        time taken: 343ms

Testing selectionsort

        time taken: 962ms

```

Mergesort on huomattavasti nopeampi kuin selectionsort.
