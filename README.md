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

- [X] Email verification
- [x] Back4app backend
- [ ] Create different classes categories
- [ ] Professor categories within class categories
- [ ] Organize notes by category
- [ ] Upload pictures of notes to the classes
- [X] Navigation bar to go between class list, compose, and profile
- [ ] Professor/student roles
- [ ] Lock app during exam time


**Optional Nice-to-have Stories**

- [ ] Verify njit email
- [ ] Report system
- [ ] Upload other file types (voice/video recording, files)
- [ ] Upload multiple files
- [ ] Class chat window


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

### [BONUS] Digital Wireframes & Mockups
<img src=figma_wireframe.png width=600>
### [BONUS] Interactive Prototype
<img src=figma_walkthrough.gif width=600>

## Schema 
### Models
#### User
| Property | Type | Description |
| --- | --- | --- |
| `objectId` | String | Unique id for user field (default field) |
| `updatedAt` | Date | Date and time the user was last updated |
| `createdAt` | Date | Date and time the user was created |
| `username` | String | Username of the user |
| `password` | String | Hashed password of the user |
| `emailVerified` | Boolean | True if the user’s email was verified |
| `email` | String | Email address of the user |

#### Post
| Property | Type | Description |
| --- | --- | --- |
| `objectId` | String | Unique id for Post field (default field) |
| `updatedAt` | Date | Date and time the Post was last updated |
| `createdAt` | Date | Date and time the Post was created |
| `description` | String | Description of the Post |
| `image` | File | Image file of the Post |
| `user` | Pointer | User who created the Post |
| `class_professor` | String | Class and professor name |
| `class_folder` | String | Name of categorized notes within a class |

#### Session
| Property | Type | Description |
| --- | --- | --- |
| `objectId` | String | Unique id for Session field (default field) |
| `sessionToken` | String | Token associated with the User’s session |
| `user` | Pointer | The User that the session is associated with |
| `createdAt` | Date | Date and time the Session was created |
| `updatedAt` | Date | Date and time the Session was last updated |
| `expiresAt` | Date | Date and time that the User’s session expires |

### Networking
#### List of network requests by screen
* Register
  * (Create/POST) New user account 
    ```Java
    //Valid-email verification 
    user.setUsername(username);
    user.setPassword(password);
    // Invoke signUpInBackground
    user.signUpInBackground(new SignUpCallback() {
        public void done(ParseException e) {
            if (e == null) {
	              //go to Login
                //Register Success
            } 
            else {
                //Unsuccessful register attempt
                return;
            }
        }
    });
    ```
* Login
  * (Read/GET) 
    ```Java
    ParseUser.logInInBackground(username, password, new LogInCallback() {
        @Override
        public void done(ParseUser user, ParseException e) {
            if (e != null) {
                //Unsuccessful login attempt
                return;
            }
	              //go to home screen
                //Login Success
        }
    });
    ```
* Notes
  * (Read/GET) Query all posts from user
    ```Java
    ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
    query.include(Post.KEY_USER);
    query.setLimit(20);
    query.addDescendingOrder(Post.KEY_CREATED_KEY);
    query.findInBackground(new FindCallback<Post>() {
        @Override
        public void done(List<Post> posts, ParseException e) {
            if(e != null ){
                //unsuccessful in retrieving posts
                return;
            }
            for(Post post:posts){
                //successful in retrieving posts
                adapter.notifyDataSetChanged();
            }
        }
    });
    ```
  * (Update/PUT)
    ```Java
    ParseQuery<ParseObject> query = ParseQuery.getQuery(Post.class);
    // Retrieve the object by id
    query.getInBackground("HMcTr9rD3s", new GetCallback<ParseObject>() {
    	public void done(ParseObject user, ParseException e) {
    		if (e == null) {
    		// Update with new data will get sent to the Parse Cloud
      		user.put("image", imageVariable);
      		user.put("description", "this is a new description for the post");
      		user.saveInBackground();
    		} else {
      			// Failed
     		}
    	}
    });
    ```
  * (Delete) 
    ```Java
    ParseQuery<ParseObject> post= ParseQuery.getQuery(Post.class);
    // Query parameters based on the item name
    Post.whereEqualTo("objectId", "HMcTr9rD3s");
    Post.findInBackground(new FindCallback<ParseObject>() {
    	@Override
    	public void done(final List<ParseObject> post, ParseException e) {
        	if (e == null) {
                	post.get(0).deleteInBackground(new DeleteCallback() {
          			@Override
        			public void done(ParseException e) {
          				if (e == null) {
            					//Deleted successfully
          				} else {
            					//Unsuccessful delete
          				}
        			}
      			});
    		} else {
      			Log.e("Activity", "Something went wrong", e);
    		}
	}
    });
    ```
* Compose Notes
  * (Create/POST) Create new Post object
    ```Java
    Post post = new Post();
    post.setDescription(description);
    post.setImage(new ParseFile(photoFile));
    //set other post attributes
    post.saveInBackground(new SaveCallback() {
        @Override
        public void done(ParseException e) {
            if (e != null) {
                //unsuccessful post save
            }
                //Successful post save
                //reset other post attributes
	              //go to posts stream 
        }
    });
    ```
* Profile-Logout
  ```Java
  ParseUser.logOut();
  ParseUser currentUser = ParseUser.getCurrentUser();
  //Go to Login screen
  //Logout successful
  ```
