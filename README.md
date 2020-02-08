*(This project was a part of studying at Kasetsart University, Thailand)*

[Overall]

The pre-process of Optical Character Recognition (OCR) of Thai characters.

This software will read the pictures of Thai characters (from Thai OCR Corpus of NECTEC, www.nectec.or.th) to be training set.

Then, the software will extract the feature of each charator.

The algorithm of feature extraction of each charactor is that each charatore will be divided into 3x3 or 4x4 parts. Each part will be counted the ratio of black coulor and white coulor.

For example, charactor which is devided into 4x4 parts, therefore there are 16 parts. When each part is counted the ratio of black and white, consequently, each charator has specific features (or 16 features for each).

....
For reading Thai charactor. 

The software will read picture of charactor and then transform to be black and white colour.

Then, the software will compare between the features of this charactor and exsisting features.
