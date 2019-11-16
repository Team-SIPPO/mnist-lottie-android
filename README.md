# lottie sample program

if you want use animation in Android, you can use lottie program provided by airbnb.  
This is minimum sample of using lottie.

## How to use
### 1. Write it down into the gradle file.
```
dependencies {
    implementation 'com.airbnb.android:lottie:3.2.0'
}
```
### 2. Get animation file from the website or Adobe After Effect.
download json file from website: [https://lottiefiles.com/](https://lottiefiles.com/)
and move to downloaded file to ```res/raw/```

### 3. Add View Component to the layout xml file.
In this sample file, the code is in the layout/content_main.xml 
```
    <com.airbnb.lottie.LottieAnimationView
        android:id="@+id/animation_view"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView"
        app:lottie_autoPlay="false"
        app:lottie_loop="false"
        app:lottie_rawRes="@raw/favourite_star" />
```

### 4. Write java code for control of the animation view.
In this sample, MainActivity has the sample code.
```LottieAnimationView ```is the main class of controlling animation view.  
About more the detail, you can see the [lottie website](http://airbnb.io/lottie/#/android?id=what-is-the-impact-of-lottie-on-apk-size)
