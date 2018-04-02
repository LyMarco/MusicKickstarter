# YOUR PRODUCT/TEAM NAME

 > _Note:_ This document is meant to be written during (or shortly after) your initial planning meeting.     
 > It does not really make sense for you to edit this document much (if at all) while working on the project - Instead, at the end of the planning phase, you can refer back to this document and decide which parts of your plan you are happy with and which parts you would like to change.


## Iteration 3

 * Start date: March 12, 2018
 * End date: April 1, 2018

## Process

(Optional:) Quick introduction

#### Changes from previous iteration

List the most significant changes you made to your process (if any).

 * At most 3 items
 * Start with the most significant change
 * For each change, explain why you are making it and what you are hoping to achieve from it
 * Ideally, for each change, you will define a clear success metric (i.e. something you can measure at the end of the iteration to determine whether the change you made was successful)

 > *Note:* If you are not making any changes to your process, it means that you are happy with all of the decisions you made in the previous iterations.
 > In this case, list what you consider to be the most significant process decisions your team made. For each decision, explain why you consider it successful, and what success metric you are using (or could use) to assert that the decision is successful.
 
 * 1. More rebasing and no committing to master directly and instead always use pull requests from separate branches
 	* We ran into some problems last iteration where some members of the group had their work removed from the master branch, causing unnecessary steps to be taken to reapply their changes. We wish to keep this from happening again and if we can rebase our branches before making a pull request, this should allow those wanting to make changes to master to see what it is they will be merging into and allw for them to figure out how to deal with these changes before actually merging to master. If we can keep from having to go back in commit history to reapply certain changes due to one member overwriting work of another, we will count this as a success.
	

#### Roles & responsibilities

Describe the different roles on the team and the responsibilities associated with each role.

(Mostly the same as iteration 2)
 *  General Software Developer: Everyone will be playing the same roles in terms of managing the project (no specified Scrum Master). However, we will be separating the project into different areas of development, where the roles are as follows,
	* Serialization: Terrence will be responsible for determining how text will be saved in the application. This includes researching how Android's file system can be used as well as creating classes/methods which can be used by other members of the group to save data easily.
	* UI: Neil will develop an interface to be used for the app. All interface questions and/or ideas will go through him.
	* Recording and Audio: Marco will be at the forefront of designing and researching audio playback and recording for the app. This includes making sure audio files are saved and accessed properly. This is a central point of focus in the app. Marco will also collaborate with Terrence to create a menu for switching between/creating new songs.
	* Lyric Suggestions: Nikita will be in charge of developing a lyric suggesting functionality which gives the user ideas for the lyrics of the song they will be writing by suggesting words which rhyme with a certain chosen word. This includes creating a server from which to fetch the suggestions as well as designing algorithms which find useful suggestions. This is also a highlighted functionality for our application.
	* Other areas: 
		* The metronome and all of its features will be designed by Patrick.
		* Tony and Joshua will develop the notes and lyrics sections of the application, respectively, along with any algorithms needed to make these more user friendly.


#### Events

Describe meetings (and other events) you are planning to have:

 * When and where? In-person or online?
 * What's the **purpose** of each meeting?
 * Other events could be coding sessions, code reviews, quick weekly sync' meeting online, etc.
 
 (This didn't change at all from iteration 2)
  Our meetings will be mostly online, outside of a weekly meeting in person during tutorial. These online meetings will be held on discord, and will be at least once a week. The aim of these meetings is to get everyone caught up with what everyone is doing (sync' meetings) but will also be used to assign those who have finished their tasks for a particular session to new tasks. These will usually take place on Thursdays or Fridays and will start at either 4pm (if afternoon) or 7pm (if evening).

#### Artifacts

List/describe the artifacts you will produce in order to organize your team.       

 * Artifacts can be to-do lists, task boards, schedule(s), etc.
 * We want to understand:
   * How do you keep track of what needs to get done?
   * How do you prioritize tasks?
   * How do tasks get assigned to team members?
   
 * Main to-do list/workflow overview - (Github Projects board)
   * We use Github's Projects board to display all the different tasks that need to be done and that have been done. The different columns in the board show whether a task is done, currently in progress, still yet to be started, etc. From here we can see also who has been assigned to each task. Also, we will be using the tags that Github offers for issues to categorize the different tasks (see below) according to epics which we defined in iteration 1.
 * Assigning jobs - (Github issues/Discord meetings)
   * Because issues in Github can be assigned to different users, we set each task in the project board to an issue and assign members to these tasks through these issues. The actual deciding of who gets assigned to each task is done via interacting on Messenger or during one of our Discord meetings
 * Prioritizing tasks - (Github Projects board/Discord meetings)
   * We will create a separate project board on Github (from the tasks board described above) for bugs, questions, etc. There will be different columns in this board for app-crashing bugs, feature bugs, and general 'feature or bug' disputes. We will also be reasigning people (as we have in the past) to more important tasks as the iteration comes to a close in order to get the more necessary jobs done.
 * Main chat/event and feature planning - (Messenger)
   * We will use Messenger, as we have been so far, as our main method for communicating. This has worked well so far as it is really convenient for setting up/rescheduling meetings without much delay. It also makes for an easy way to get opinions on features quickly, or let each others know if their work has a bug that needs fixing immediately.

#### Git / GitHub workflow

Describe your Git / GitHub workflow.     
Essentially, we want to understand how your team members share a codebase and avoid conflicts.

 * Be concise, yet precise.      
For example, "we use pull-requests" is not a precise statement since it leaves too many open questions - Pull-requests from where to where? Who reviews the pull-requests? Who is responsible for merging them? etc.
 * If applicable, specify any naming conventions or standards you decide to adopt.
 * Don't forget to **explain why** you chose this workflow.
 
  * Each epic/main role of the project will have its own branch in the repository. Each member will work on the branch of their role and merge it using pull-requests with the master branch once a certain defined task or feature has been implemented fully. The pull-request may be handled by the person who developed the feature. However, the person who made the pull request may additionally enlist the assistance of another group member to review the request and have them confirm the merge. Most commonly, we alert/update all other members of the pull-request prior to commiting it to master branch via Messenger for any members who wish to look the merge over and sign off on its viability. We wish to solve as many problems/conflicts as we can before the merge though and so we will try to make it almost mandatory to rebase our branch with master before we make the pull request.
 
 * This should keep the master branch always in a stable state and should keep it so that everything in the master branch works according to a certain set of features/tasks. Ideally the features of the master branch should coincide with the tasks in the 'done' section of the project board. Thus, the progress of the project is always well-defined. This also allows each member of the group to work in their own space without having to constantly deal with other members' bugs and it allows each member to know by looking at the master branch what he has to work with while developing his own features.

 * We leverage the GitHub projects boards and To-Do lists in our planning phase, and intend to use it as a good reference tool to see what is currently being worked on by other members, what features are completed in the current master, and what things we have planned for the future. Maintaining these boards will be very important to streamlining our process and keep everyone on the same page without constant face-to-face meetings.
 
 * In addition to using Github Projects boards for planning and reference, we will begin using it as a place to collect bugs and possibly questions concerning certain features that should/should not be, implemeneted. This will be done using both the entitled Issues feature as well as the Projects board in Github for classification and an overview of the different bugs that need working on.




## Product

#### Goals and tasks

 * Describe your goals for this iteration and the tasks that you will have to complete in order to achieve these goals.
 * Order the items from most to least important.
 * Feel free (but not obligated) to specify some/all tasks as user stories.

 * (Goal) Setup server in the cloud (as opposed to on ones PC/MAC) for lyric suggestions and have suggestion placed in text when tapped
	* (Tasks)
   	* Create Web App Service on Azure
	* Deploy Suggestions Server to Azure
	* Make suggestions clickable
	* Create methods for inserting text into a desired position in the textbox
		
 * (Goal) Saving multiple songs each with multiple takes
	* (Tasks)
	* Group lyrics, recordings by songs
	* Save each of these as a separate song and allow these songs to be retreived again
	* Save multiple recordings for a single song
	* Allow renaming of songs/takes
	
* (Goal) Make metronome more dynamic
	* (Tasks)
	* Make upbeat modifiable (2/4ths time, 3/4ths time, etc.)
	* Add more speed options
	* Add in drums as an extra feature
	
* (Goal) Enhance UI and overall flow of application, especially in the notes-taking part of the app
	* (Tasks)
	* Add more image buttons to create more intuitive and friendly feel to the app
	* Make navigation of the app more intuitive
	* Create separate page for actually writing notes
	
* (Goal) Allow for chords (e.g. 'A', 'C#', etc.) to be placed above lyrics
	* (Tasks)
	* Create separate text fields for chords
	* Group text fields by verses
	* Think of way to allow editing of both lyrics and chords without while keeping everything simple and intuitive
	* Make chords stay on top of the lyrics they were placed above even while editing

##### Overflow from previous iteration
		
* Audio Playback
	* Play back audio for multiple tracks
	* Allow the user to dictate the name of the tracks recorded

 * User can go to notes screen, create notes, and view contents of each note
	* Allow a 2-dimensional notes interface
		* That is, one page will contain Note Titles, while the second "layer" consists of pages with note contents
	
* Metronome
	* Set and be able to change speed of beat at fixed speeds.
	* UI
* Serialization
	* Saving multiple recordings and lyrics


#### Artifacts

List/describe the artifacts you will produce in order to present your project idea.

 * Artifacts can be text, code, images, videos, interactive mock-ups and/or any other useful artifact you can think of.
 * Make sure to explain the purpose of each artifact (i.e. Why is it on your to-do list? Why is it useful for your team?)
 * Be concise, yet precise.         
   For example: "Build the website" is not precise at all, but "Build a static home page and upload it somewhere, so that it is publicly accessible" is much clearer.

* Lyrics Suggestion API
	* Should be available online and not require a local server
	* Endpoint `/suggestions/word` should return rhymes and extra details about the word
	* Endpoint `/rhymes/word` should return just the rhymes at a faster speed
