# Team 11

 > _Note:_ This document is meant to be written during (or shortly after) your initial planning meeting.     
 > It does not really make sense for you to edit this document much (if at all) while working on the project - Instead, at the end of the planning phase, you can refer back to this document and decide which parts of your plan you are happy with and which parts you would like to change.


## Iteration 02

 * Start date: February 12th 2018
 * End date: March 11th 2018

## Process

Quick introduction
 
 * Appoint everyone different roles and lay out a general idea for how the entire iteration process should work for iteration 1. Begin work on the project and have a stable, fairly reasonably featured application working for deliverable 2.

#### Roles & responsibilities

Describe the different roles on the team and the responsibilities associated with each role.
 *  General Software Developer: Everyone will be playing the same roles in terms of managing the project (no specified Scrum Master). However, we will be separating the project into different areas of development, where the roles are as follows,
	* Serialization: Terrence will be responsible for determining how text will be saved in the application. This includes researching how Android's file system can be used as well as creating classes/methods which can be used by other members of the group to save data easily.
	* UI: Neil will develop an interface to be used for the app. All interface questions and/or ideas will go through him.
	* Recording and Audio: Marco will be at the forefront of designing and researching audio playback and recording for the app. This is a central point of focus in the app.
	* Lyric Suggestions: Nikita will be in charge of developing a lyric suggesting functionality which gives the user ideas for the lyrics of the song they will be writing by suggesting words which rhyme with a certain chosen word. This includes creating a server from which to fetch the suggestions as well as designing algorithms which find useful suggestions. This is also a highlighted functionality for our application.
	* Other areas: 
		* The metronome which will be included in the app will be designed by Patrick.
		* Tony and Joshua will develop the notes and lyrics sections of the application.

#### Events

Describe meetings (and other events) you are planning to have:

 * When and where? In-person or online? 
 * What's the purpose of each meeting?
 * Other events could be coding sessions, code reviews, quick weekly sync' meeting online, etc.
 
 Our meetings will be mostly online, outside of a weekly meeting in person during tutorial. These online meetings will be held on discord, and will be at least once a week. The aim of these meetings is to get everyone caught up with what everyone is doing (sync' meetings) but will also be used to assign those who have finished their tasks for a particular session to new tasks. These will usually take place on Thursdays or Fridays and will start at either 4pm (if afternoon) or 7pm (if evening).

 * Feb 12th 
	* In-person
 	* Break down features into tasks
	* Assign tasks to be researched by each person interested in working on a task
 * Feb 15th 
	* Online
	* Review the difficulty and implementation of tasks, ensure each person has a plan to start working on their task, or adjust if there are difficulties (The tasks need to be broken down further, more people or time is required to complete the task by Deliverable 2 Sprint)


#### Artifacts

List/describe the artifacts you will produce in order to organize your team.       

 * Artifacts can be To-do lists, Task boards, schedule(s), etc.
 * We want to understand:
   * How do you keep track of what needs to get done?
   * How do you prioritize tasks?
   * How do tasks get assigned to team members?
 
* We will be using Github's Projects section to define somewhat of a master board of ideas and tasks. These tasks will be categorized by the different epics which we defined in the roles secton (e.g. U.I., Metronome, Lyric Suggestions, etc.). Once a task is defined, it will be categorized and placed in a 'to-do' section of the board, and will move from there to 'in progress' and finally to 'done.' These tasks will also be set up as issues in Github where we may comment on various features and/or bugs of the task as it is in progress. Other issues may also refer to this issue if it is believed to be directly related to this task. Furthermore, each of these tasks will be assigned to a member of the group. This way, tasks are well-defined, we know who to talk to when we have an issue with a certain part of the project, and we can talk about certain features of the project app and everyone knows what we are referring to.

	
#### Git / GitHub workflow

Describe your Git / GitHub workflow.     
Essentially, we want to understand how your team members share a codebase and avoid conflicts.
 
 * Each epic/main role of the project will have its own branch in the repository. Each member will work on the branch of their role and merge it using pull-requests with the master branch once a certain defined task or feature has been implemented fully. The pull-request will be handled by the person who developed the feature. If anything is broken as a result of a merge, it is the responsibility of the person who made the merge to fix it.

 * The person who made the pull request may additionally enlist the assistance of another group member to review the request and have them confirm the merge. Most commonly, we alert/update all other members of the pull-request prior to commiting it to master branch via Messenger for any members who wish to look the merge over and sign off on its viability.
 
 * This should keep the master branch always in a stable state and should keep it so that everything in the master branch works according to a certain set of features/tasks. Ideally the features of the master branch should coincide with the tasks in the 'done' section of the project board. Thus, the progress of the project is always well-defined. This also allows each member of the group to work in their own space without having to constantly deal with other members' bugs and it allows each member to know by looking at the master branch what he has to work with while developing his own features.

 * As an example,
	* Joshua's role for the current itertion is to develop the lyrics activity (screen where the user can write the lyrics to their song). 
	* The different tasks Josh will be assigned to will include creating editable texts and being able to separate these texts into verses and titles of verses
	* These tasks will be defined as issues, placed in the 'to-do' section of the board, and tagged as 'Lyrics'
	* Josh will need to colaborate with Terrence who is working with serialization/saving of text. 
	* Once Terrence has developed his branch enough to handle saving of texts, Josh and Terrence can merge their branches to support the saving of the lyrics for a song and then merge with master to indicate a fully developed feature.
	* If at any time during this process, Neil merges an update to the UI into master, Josh can pull in those changes and deal with them before he merges his own branch with master.


## Product

#### Goals and tasks
 
 * Our app should be working and able to record and playback audio.
	* (Tasks)
	* Playback sounds.
	* Record audio from microphone.
	* Save audio at least long enough so that it can be played back.
	* (Optionally)
	* Save audio for multiple tracks
	* Play back audio for multiple tracks
	* Allow the user to dictate the name of the tracks recorded
 * We would like there to be a fairly developed UI for the end of this iteration.
	* (Tasks)
	* Create a prototype for the UI.
	* Develop main screen so that lyrics are displayed at the same time that voice recording can occur.
	* Provide mock-ups for all features so that development can have a visual-aid in mind when working
 * There should be a main screen where the user can write and edit lyrics to a song.
	* (Tasks)
	* Have editable texts for lyrics.
	* Separate lyrics by verses and titles.
	* Add more verses
	* Delete verses
	* Provide a scrollable view to review all lyrics and verses written
 * There should at least be a place for writing miscellaneous notes.
	* (Tasks)
	* Allow a 2-dimensional notes interface
		* That is, one page will contain Note Titles, while the second "layer" consists of pages with note contents
 * We aim to have at least a somewhat functional metronome.
	* (Tasks)
	* Playback sound at a fixed beat.
	* Set and be able to change speed of beat.
	* Beat persists throughout all screens of the application.
	* Have a distinct downbeat in 4/4 time
 * Basic saving of text and audio would be ideal.
	* (Tasks)
	* Notes and lyrics should persist throughout use of application.
	* Should be able to save recording of songs.

#### Artifacts

List/describe the artifacts you will produce in order to present your project idea.
   
 * Prototype for UI: Shows the interface that users will see once the app is finished. Gives the group a feel for what they are designing and for how the features they develop will be used.

 * Page Mock-ups: Mock-ups for each featural page, including, but not limited to, the notes page and metronome page, as they will be separate activities with a look that should be distinct from the main page, and will require a visualization

