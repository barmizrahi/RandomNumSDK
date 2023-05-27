# RandomNumSDK

[![](https://jitpack.io/v/barmizrahi/RandomNumSDK.svg)](https://jitpack.io/#barmizrahi/RandomNumSDK)

Add it in your root build.gradle at the end of repositories:

## 1. allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
## 2. Add the dependency

	dependencies {
	        implementation 'com.github.barmizrahi:RandomNumSDK:1.0.0.0'
	}
  
  # Usage
  
  ## 1. Create an instance of RandomNumberSDK:

     RandomNumberSDK randomNumberSDK = new RandomNumberSDK();
     
   ## 2. Call the getRandomNumber() method to get a random number. This method need to implamens two function onRandomNumberGenerated() , onRandomNumberError().
   
      randomNumberSDK.getRandomNumber(new RandomNumberSDK.RandomNumberListener() {
         @Override
         public void onRandomNumberGenerated(int randomNumber) {
         // Use the generated random number
            Toast.makeText(MainActivity.this, "Random Number: " + randomNumber, Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onRandomNumberError(String errorMessage) {
          // Handle error
             Toast.makeText(MainActivity.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
           }
        });
