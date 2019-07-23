# Learn how to build a driver distration application using Visual Recognition on Watson Studio

## Introduction
Each day in the United States, approximately 9 people are killed and more than 1,000 injured in crashes that are caused becasue the driver was distracted (cdc.gov/motorvehiclesafety/distracted_driving/). This is an alarming number of accidents for any country and a prevention method must be used. 
AI can be a solution to this problem. The government can install IOT cameras around the city to monitor these drivers and promote safer driving by applying strict rules. This blog shows how easy is to implement this project by using IBM Watson Studio on the Cloud.


## What is Watson Studio
IBM Watson Studio is a full suite of data science tools that can be used by any citizen data scientist to implement their AI project with minimal coding. The tool includes tools such as Data Refinery for data preparation to tools like SPSS modeler to create a machine learning model without any code. In this blog we will be using the Visual Recognition service and see how to create a image classifier within 30 mintues without any code. 


## How to create the application 
We will first start off by creating the service on Watson Studio and building the model on it. We will then integrate the model into our end application to see it live in action. So lets get started ... 

### Visual Recognition using Watson Studio 

#### Steps: 

1. Create a Visual recognition Watson Studio project
<img src = "https://github.com/anchalbhalla/detecting-distracted-driver-cloud/blob/master/images-gifs/project.png">
2. Get the image dataset from this kaggle link: https://www.kaggle.com/c/state-farm-distracted-driver-detection
</br>3. Drag and drop the zip folder to train the model. Once thats done you will see all the 10 classes of distraction including one negative class (c0- safe driver) 
<img src = "https://github.com/anchalbhalla/detecting-distracted-driver-cloud/blob/master/images-gifs/visual.png">
4. Send the data for train and wait for the results

### Dashboarding to check class imbalance (Optional)
One main problem in data science is class imbalance. Class imbalance occurs when the data in each class of the model does not have an equal number of examples for training. This can make lower the model accuracy and give wrong results.
A simple dashboard is created to see how varied is the image dataset. This dashboard was created using Cognos Dashboards. 

<img src = "https://github.com/anchalbhalla/detecting-distracted-driver-cloud/blob/master/images-gifs/dashboard.png">

## Android Application 

#### Steps: 
1. Download the Android project 
2. Edit the visual recognition credentials in the values/strings.xml file 
<img src = "https://github.com/anchalbhalla/detecting-distracted-driver-cloud/blob/master/images-gifs/api-key.png">
3. Edit the model id in the MainActivity.java file 
<img src = "https://github.com/anchalbhalla/detecting-distracted-driver-cloud/blob/master/images-gifs/model-id.png"> 

## Demo of the application in action
<img src = "https://github.com/anchalbhalla/detecting-distracted-driver-cloud/blob/master/images-gifs/app-demo.gif">
