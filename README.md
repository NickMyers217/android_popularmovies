## PopularMovies
This is an Android app that displays a list of popular movies
playing in theatres. It uses [TMDB](https://www.themoviedb.org/?language=en)
data from the internet to show the current box office hits.
It also has the ability to show TMDB's all time highest
rated movies according to their users.

## Setup
To run this app you'll need an up to date version of Google's
Android Studio. Start by cloning or downloading this repo:
```shell
git clone https://github.com/NickMyers217/android_popularmovies
```
Next open up android studio and choose to import the repo
folder as a project. You'll need to setup either a virtual
device or USB debuggable physical device to run the app
on.

## Get an API Key
Before you can successfully run the app you'll need to get
an API key from [TMDB](https://www.themoviedb.org/?language=en).
Go to their website, make an account, then sign up for an API key.
Once you are approved for a key, you'll need to copy it
and replace the value of of the API_KEY variable in TheMovieDbApi.java
with your key. Once you have completed this step, the app
should run correctly.


