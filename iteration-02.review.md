# Team 11

 > _Note:_ This document is meant to be written during (or shortly after) your review meeting, which should happen fairly close to the due date.      
 >      
 > _Suggestion:_ Have your review meeting a day or two before the due date. This way you will have some time to go over (and edit) this document, and all team members should have a chance to make their contribution.


## Iteration 02 - Review & Retrospect

 * When: March 8th 2018
 * Where: Online, Discord group call

## Process - Reflection

(Optional) Short introduction

#### Decisions that turned out well

List process-related (i.e. team organization) decisions that, in retrospect, turned out to be successful.

 * We decided not to keep our development focused solely on each member's assigned feature. Even though roles are pretty concrete, if any one member was struggling with a task, then another member would make themselves available to help. The prime examples of this include:
	* When Tony was having trouble familiarizing with Android and porting his Java-coded work to Android Studio to make it viable in our app, Joshua helped out in creating the notes section, porting over much of his lyrics-writing functionality to give us a minimum viable product while things while Tony was familiarizing himself with Android development
	* Joshua worked closely with Nikita during the lyrics suggestion interaction with the android app, which allowed Nikita to focus more on how the lyrics are generated rather than how one implements the suggestions in the first place
	* This process-related decision helped development run smoother since it allowed more minds to go toward fixing a particular problem and allowing overall balancing of resources.
 * That being said, our decision to assign primary roles to individuals also helped us focus on our specific tasks, and many of these decisions leveraged the strengths of individual members
	* For example, Nikita has worked most with node.js and server-related functions, and took it upon himself to dedicate his time towards the vital lyrics-suggestion feature of the application. His knowledge made the perfect fit for the role as he effectively developed his feature in a way most other members would not know how to begin.
	* Another example is Neil's work on UI. He is the most talented graphic designer amongst the group, and any request we put in for visual application resources, Neil is more than up for the task.
 * Using Facebook Messenger as a communication tool was a good decision because everyone had convenient access to it and it was less likely for someone to be absent as it is an ongoing chat. It became very useful for asking quick questions or making small decisions on implementation details without having to host a full-scale Discord meeting. The personal tagging was useful to submit questions to specific individuals, and decisions could be made in a continuous manner without relying on any synchronous availability
 * The process-related decision to use GitProjects to keep a list of Epics, To-Do, In Progress, and Completed tasks proved to be of use, especially in knowing the broad scope of the current landscape of the application
	* An example for the usefulness of the boards comes from our update meetings. Whenever someone wanted to know what had been completed that week, and what more needed to be done for the rest of iteration 02, we could refer to the boards to get a quick once-over glance at what the state of our development was like so far. 

#### Decisions that did not turn out as well as we hoped

List process-related (i.e. team organization) decisions that, in retrospect, were not as successful as you thought they would be.

 * 2 - 4 decisions.
 * Ordered from most to least important.
 * Feel free to refer/link to process artifact(s).

 * Our GitHub workflow that we had planned out (having everyone work on their standalone features in a branch and then merge to master) had a rough start.
	* Those unfamiliar with the Git workflow or not understanding how branches were organized might do some messy merges that result in local files being deleted, wasting time and resources in recovering the information.
	* Deleting the work of other's by "resolving" conflicts on their own without properly reviewing the content.
	* Committing directly to master with an older version of the codebase and reverting commits made by other contributors
	* In the end, things smoothed out more, but there continue to be rough patches that will hopefully become a smooth and well-oiled machine going into interation 03
 * 
 * 
 * Naming conventions for 
	* a204d0943a010bfbe99ddaec26d5347356315443


#### Planned changes

List any process-related changes you are planning to make (if there are any)

<<<<<<< HEAD
 * Establish firm conventions for how we conduct pull-requests, merges, and commits
	* For example: NEVER directly commit anything to the codebase. All code should be merged via pull-request
	* Also any conflicts made during a pull request, if it interferes with another member's submitted code, should ALWAYS be reviewed by one or more parties before merging
	* One should always try to make merges with purpose or valuable content. 
 * Establish a naming convention for pull-requests and commits should most likely be implemented, such that the messages convey some information as to what was changed or added during the merge.
=======
 * Ordered from most to least important.
 * Explain why you are making a change.

>>>>>>> 86c0d0fc068891a362570a5de9484a66a2d62556

## Product - Review

#### Goals and/or tasks that were met/completed:

 * (Goal) Basic functionality involving recording / playback of audio
	* (Tasks)
	* Playback sounds.
	* Record audio from microphone.
	* Save audio at least long enough so that it can be played back.
	* Save audio for multiple tracks
	* Lyrics.java (the audio has been implement inside the lyrics page)
 * (Goal) UI for the Main Screen
	* (Tasks)
	* Develop main screen so that lyrics are displayed at the same time that voice recording can occur.
	* activity_lyrics.xml
 * (Goal) Basic functionality for writing / editing user lyrics
	* (Tasks)
	* Text is editable for lyrics
	* Lyrics and Verses are separated
	* Functionality of adding/deleting verses and lyrics
	* Provide a scrollable view to review all lyrics and verses written
	* Lyrics.java, LyricsSuggestion.java, content_lyrics.xml
 * (Goal) Note taking functionality
	* (Tasks)
	* A notes page was created and can be accessed.
	* Notes.java, content_notes.xml
 * (Goal) Metronome is completely functional with minor problems
  	* MetronomeSingleton.java, MetronomeActivity.java, SixteenBitSynthesizer.java, activity_metronome.xml
 * (Goal) Saving text and audio
	* (Tasks)
	* Notes and lyrics persist for one creation.
	* Recordings are saved to the device
	* Song.java, User.java, SerializationBase.java
 * (Goal) Testing Functionality
 	* (Tasks)
	* Tests were performed over FMessenger, and a collection of Debug.txts, Error reports, and other things were completed.
	* Most of the testing involved testing on an android phone and seeing if audio was recorded, the metronome played, lyrics and notes were recorded. All of this was iterative so as we developed we tested with each other.
	
#### Goals and/or tasks that were planned but not met/completed:

* Audio Playback
	* Play back audio for multiple tracks
	* Allow the user to dictate the name of the tracks recorded
Being able to play back and name different audio tracks recorded is an important part to the functionality of our application. We did not get around to writing code for this as it was not priority for this iteration and we worked more towards getting the basic functionality of recording and playing back audio finished. Going into our next iteration, we aim for this task to be done.
* UI
	* Provide mock-ups for all features so that development can have a visual-aid in mind when working
We did not provide mock-ups or prototypes for all screens. Rather most of the development here went into creating the UI for the main page and note taking pages as those are the most important parts when viewing our application. As a result the UI for metronome, documents, serialization among other things were not finished as they could function without an explicitly designed UI. We aim to have the UI complete for the parts of the application we did not finish and refine our current UI based on tests we have finished for the next iteration.
* Overall note functionality
Note taking was incomplete this iteration due to unfamiliarity with android development. At this point the note taking part of the application is currently a branch of lyrics and is still under development. Even though this part of the application is underdeveloped, we learnt a lot about working with multiple screens and having data persist and hope that this helps us with future strides in working towards this goal.
* Metronome
	* Set and be able to change speed of beat at fixed speeds.
	* UI
Most of the metronome is functional although the task above was not to finished to the extent that we wish to have it in our final iteration. Due to complications revolving around the construction of the metronome second by second, only certain speeds (multiples of 20 bps) are playable. We aim to have this in a better state by the next iteration. The UI is explained as above and is in the state of an extremely rough prototype.
* Serialization
We were able to serialize data in the application for one set of lyrics. However did not get around to complete finishing the functionality of saving different notes and lyrics in separate files and aim to finish this in the next iteration

## Meeting Highlights

Going into the next iteration, our main insights are:

 * In our Review meeting we outlined specific tasks we want each person to work on for the next iteration, breaking down larger tasks into more specific and focused tasks, and addressing problems we came across when working on this past iteration. 
 * To take our app from a Minimum Viable Product to a full-fledged, proper application, we have decided that focus and support should be directed towards our core features. Supporting features are obviously important, and should be maintained alongside the core features of the app, but to really flush out the app we need to add enhancements and details that will make the app user-friendly and viable to our target audiences as their top choice. Any core feature that needs aid, now that most minimum features have been established, should receive extra help in any way it needs. Whether it be with research or coding development. 
