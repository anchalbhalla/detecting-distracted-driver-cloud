# Detecting Distracted Drivers on the roads using Visual recognition on Android

## Visual Recognition using Watson Studio 

#### Steps: 

1. Create a Visual recognition Watson Studio project
<img src = "https://github.com/anchalbhalla/detecting-distracted-driver-cloud/blob/master/images-gifs/project.png">
2. Get the image dataset from this kaggle link: https://www.kaggle.com/c/state-farm-distracted-driver-detection 
3. Drag and drop the 1 zip folder to train the model. Once thats done you will see all the 10 classes of distraction including one negative class (c0- safe driver) 
<img src = "https://github.com/anchalbhalla/detecting-distracted-driver-cloud/blob/master/images-gifs/visual.png">
4. Send the data for train and wait for the results

## Dashboard 
A simple dashboard to see how varied is the image dataset. This dashboard was created using Cognos Dashboards. 

<img src = "https://github.com/anchalbhalla/detecting-distracted-driver-cloud/blob/master/images-gifs/dashboard.png">

## Android Application 

#### Steps: 
1. Download the Android project 
2. Edit the visual recognition credentials in the values/strings.xml file 
<img src = "https://github.com/anchalbhalla/detecting-distracted-driver-cloud/blob/master/images-gifs/api-key.png">
3. Edit the model id in the MainActivity.java file 
<img src = "https://github.com/anchalbhalla/detecting-distracted-driver-cloud/blob/master/images-gifs/model-id.png"> 

#### Demo of the application in action
<img src = "https://github.com/anchalbhalla/detecting-distracted-driver-cloud/blob/master/images-gifs/app-demo.gif">
