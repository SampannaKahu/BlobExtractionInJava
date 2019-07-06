# BlobExtractionInJava [![Build Status](https://travis-ci.org/SampannaKahu/BlobExtractionInJava.svg?branch=master)](https://travis-ci.org/SampannaKahu/BlobExtractionInJava)
## Brief Description
Given a grayscale image, this program thresholds the image with the preset threshold value and extracts black coloured blobs on white background and stores them in separate images.

## Methodology
Each pixel of the image is iterated once using loops.
While doing so, if we encounter a black pixel and it is not a part of any eisting blobs, flood-fill algorithm is used to find all the contigious blob pixels.
Instead of replacing the target pixels in the flood-fill algorithm, they are added to the Blob object.
Further, the calculated blobs are painted on separate images and stored.

## Salient features
1. Can be used to find any number of blobs.
2. Can be used to find convex or concave blobs, and also the blobs that are intertwined or form an enclave.
3. Can be used as a preliminary step of OCR/Pattern recognition.

## Example 1:
### Input image:
![abc_bw](https://cloud.githubusercontent.com/assets/10060176/12223407/c8f45e16-b7fc-11e5-9c64-c2b59807abbd.jpg)

### Output images:
1)

![abc_bw_output1](https://cloud.githubusercontent.com/assets/10060176/12223435/5320ece4-b7fd-11e5-8446-8c5b8dea8385.jpg)

2)

![abc_bw_output2](https://cloud.githubusercontent.com/assets/10060176/12223438/81957658-b7fd-11e5-99d7-d9886c2ef11b.jpg)

3)

![abc_bw_output3](https://cloud.githubusercontent.com/assets/10060176/12223446/abb5f3cc-b7fd-11e5-87de-9aa3727ac451.jpg)


## Example 2:
### Input image:
![sampletext](https://cloud.githubusercontent.com/assets/10060176/12223632/baae5068-b802-11e5-89a6-63efa084812d.png)


### Output images:
1. 
![sampletext png_outputblob0](https://cloud.githubusercontent.com/assets/10060176/12223633/bab16582-b802-11e5-80fc-91d51e9f5ff8.png)



2. 
![sampletext png_outputblob1](https://cloud.githubusercontent.com/assets/10060176/12223634/bab4fb84-b802-11e5-93ac-430ae6b9081a.png)
