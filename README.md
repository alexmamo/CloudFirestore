# CloudFirestore - Clean Architecture with MVVM.

It's an aplication build with Kotlin as an example on how to display data from a [Cloud Firestore](https://firebase.google.com/docs/firestore), using four different approaches. The first approach is using a callback, the second approach is using an [Android Architecture Component](https://developer.android.com/topic/libraries/architecture) called [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), the third one is using [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) and the fourt one using [Flow](https://kotlinlang.org/docs/flow.html). All four approaches are using the same Android Architecture Component called [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel).

To keep things simple, the app uses a very simple database schema that look like in the image below:

![alt text](https://i.ibb.co/9T2jfzh/1-ZGKXFzx-Vye7-J97-XHPuw-MQ.jpg)

To make this app work, follow the instructions given in the official documentation regarding [how to add Firebase to your project](https://firebase.google.com/docs/android/setup). Add the JSON file in your app folder, add some dummy products and see it working.

This repo is the source code of the this article: [How to read data from Cloud Firestore using get()?](https://medium.com/firebase-tips-tricks/how-to-read-data-from-cloud-firestore-using-get-bf03b6ee4953)
