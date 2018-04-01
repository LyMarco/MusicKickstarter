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

#### Roles & responsibilities

Describe the different roles on the team and the responsibilities associated with each role.

(Mostly the same as iteration 2)
 *  General Software Developer: Everyone will be playing the same roles in terms of managing the project (no specified Scrum Master). However, we will be separating the project into different areas of development, where the roles are as follows,
	* Serialization: Terrence will be responsible for determining how text will be saved in the application. This includes researching how Android's file system can be used as well as creating classes/methods which can be used by other members of the group to save data easily.
	* UI: Neil will develop an interface to be used for the app. All interface questions and/or ideas will go through him.
	* Recording and Audio: Marco will be at the forefront of designing and researching audio playback and recording for the app. This includes making sure audio files are saved and accessed properly. This is a central point of focus in the app. Marco will also collaborate with Terrence to create a method for switching between/creating new songs.
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

#### Git / GitHub workflow

Describe your Git / GitHub workflow.     
Essentially, we want to understand how your team members share a codebase and avoid conflicts.

(Same as iteration 2 for now but should be changed)
 * Be concise, yet precise.      
For example, "we use pull-requests" is not a precise statement since it leaves too many open questions - Pull-requests from where to where? Who reviews the pull-requests? Who is responsible for merging them? etc.
 * If applicable, specify any naming conventions or standards you decide to adopt.
 * Don't forget to **explain why** you chose this workflow.
 
  * Each epic/main role of the project will have its own branch in the repository. Each member will work on the branch of their role and merge it using pull-requests with the master branch once a certain defined task or feature has been implemented fully. The pull-request will be handled by the person who developed the feature. If anything is broken as a result of a merge, it is the responsibility of the person who made the merge to fix it.

 * The person who made the pull request may additionally enlist the assistance of another group member to review the request and have them confirm the merge. Most commonly, we alert/update all other members of the pull-request prior to commiting it to master branch via Messenger for any members who wish to look the merge over and sign off on its viability.
 
 * This should keep the master branch always in a stable state and should keep it so that everything in the master branch works according to a certain set of features/tasks. Ideally the features of the master branch should coincide with the tasks in the 'done' section of the project board. Thus, the progress of the project is always well-defined. This also allows each member of the group to work in their own space without having to constantly deal with other members' bugs and it allows each member to know by looking at the master branch what he has to work with while developing his own features.

 * We leverage the GitHub projects boards and To-Do lists in our planning phase, and intend to use it as a good reference tool to see what is currently being worked on by other members, what features are completed in the current master, and what things we have planned for the future. Maintaining these boards will be very important to streamlining our process and keep everyone on the same page without constant face-to-face meetings.

 * As an example,
	* Joshua's role for the current itertion is to develop the lyrics activity (screen where the user can write the lyrics to their song). 
	* The different tasks Josh will be assigned to will include creating editable texts and being able to separate these texts into verses and titles of verses
	* These tasks will be defined as issues, placed in the 'to-do' section of the board, and tagged as 'Lyrics'
	* Josh will need to colaborate with Terrence who is working with serialization/saving of text. 
	* Once Terrence has developed his branch enough to handle saving of texts, Josh and Terrence can merge their branches to support the saving of the lyrics for a song and then merge with master to indicate a fully developed feature.
	* If at any time during this process, Neil merges an update to the UI into master, Josh can pull in those changes and deal with them before he merges his own branch with master.




## Product

#### Goals and tasks

 * Describe your goals for this iteration and the tasks that you will have to complete in order to achieve these goals.
 * Order the items from most to least important.
 * Feel free (but not obligated) to specify some/all tasks as user stories.

 * (Goal) We should have the ability to get lyrics suggestion based on a selected word 
	* (Tasks)
   	* User highlights a word to have it selected
   	* User clicks on the question mark button to get suggestions for word from server
   	* Suggested words are displayed on the bar at the bottom of the screen

		
 * (Goal) Saving text and audio
	* (Tasks)
	* Save and View multiple sets of lyrics
	* Save and Play multiple recordings

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
