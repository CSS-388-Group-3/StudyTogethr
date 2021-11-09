Original App Design Project - README Template
===

# StudyTogethr

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
StudyTogethr is an app for students to collaborate and share their class notes. Students can take pictures of their notes and upload them to different class categories so other students can see them if they missed class or want to compare.

### App Evaluation
- **Category:** Educational
- **Mobile:** It needs to be mobile because students would use it during class to upload their notes once they finish them, and view other students' notes if they need them during class.
- **Story:** App lets students post their notes and categorize them based on class/professor. Other students can then view those notes.
- **Market:** College students.
- **Habit:** This app could be used every time students have class in order to upload their notes.
- **Scope:** First we will start with basic photo uploading and viewing capabilities. Then we'll move on to categorization and features to help protect academic integrity.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* Email verification
* Back4app backend
* Create different classes categories
* Professor categories within class categories
* Organize notes by category
* Upload pictures of notes to the classes
* Navigation bar to go between different classes and groups of files in the class
* Professor/student roles
* Lock app during exam time


**Optional Nice-to-have Stories**

* Verify njit email
* Report system
* Upload other file types (voice/video recording, files)
* Upload multiple files
* Class chat window


### 2. Screen Archetypes

* Login
* Register - user can create an account or use login
   * On opening the app, if the user isn't already logged in, they are prompted to enter their email and a password
   * User also selects if they are a professor or student
* Class selection screen - Screen to select different classes
   * User can see a screen with all the classes listed that they can scroll through and select which class they want to see notes from
* Class Screen
   * Upon selecting a class, the user can see a list of categories/folders which the notes are organized into
   * When they select a category, they can see the photos of the notes in a scrolling view
   * If the user is a professor, they can choose to lock the class at a specified time
   * (optional story) users can select a post and hit a report button in order to have it reviewed and possibly removed
   * (optional) chat for the class
* New post Screen
   * The user can choose a class and professor, and then take a picture (or upload a file as an optional story)
   * The file is then stored in the backend
* Profile
   * Allows user to see their status as a student / professor, and (optional) see their posts
   * (optional) User can log out


### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Class selection
* New post
* Profile

**Flow Navigation** (Screen to Screen)

* Login
   * -> Register if they don't have an account
   * -> Class selection screen if they log in successfully
* Class selection
   * -> Class screen
* New post -> take photo
* Profile -> view profile status, logout

## Wireframes
[Add picture of your hand sketched wireframes in this section]
<img src="YOUR_WIREFRAME_IMAGE_URL" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
[This section will be completed in Unit 9]
### Models
[Add table of models]
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]