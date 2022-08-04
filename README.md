# Aural Analytics Android Challenge

This Android project serves as a starting point to build off of in this code challege. The primary goal is to see your programming skills and Android knowledge applied to a specific set of tasks.

### Setup

This simple Android app is buildable and runs as-is, and already includes the time consuming boilerplate needed for any Android project. At a high level, the app makes an API call to Reddit to grab posts for a subreddit, and shows them in a list. The `ListFragment` uses a `PagingDataAdapter` for the `RecyclerView`, which is a seldom-used adapter in the AndroidX suite. The way the API call is made is simplistic and opaque, but works.

### Objectives

The ask for this challenge is to complete the following objectives:

1. Add tests for the `ListViewModel`, at a minimum covering the `FromAdapter` action. `JUnit` is included already for this, but feel free to utilize another testing framework if there's one you feel more comfortable with.
1. When a post has an image, show that image in the list.
1. Show post title, author, and image (if the post has one) on the `PostFragment` when a post item is tapped. Don’t worry too much about design as long as the objective is met.

Additional considerations for this challenge:
- The use of additional 3rd party libraries is acceptable. But be preparred to defend the use of any such libraries.
- The minimum SDK version must stay at most 23.

### Submitting

To submit a response to the challenge, first clone this repo to work on it. Forking and pull requests are not advised in order to keep individual responses separate. Your additions to the code should then be commtted to a new public repo you create (for example on Github or Gitlab). The app must be able to be pulled down from this repo and built without any additional configuration. And to ensure reviewing is as straightforward as possible, please keep the existing git history and add your response as additional commits.
