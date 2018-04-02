# Team 11

 > _Note:_ This document is meant to be written during (or shortly after) your review meeting, which should happen fairly close to the due date.      
 >      
 > _Suggestion:_ Have your review meeting a day or two before the due date. This way you will have some time to go over (and edit) this document, and all team members should have a chance to make their contribution.


## Iteration XX - Review & Retrospect

 * When: FILL IN THE DATE WHEN YOU ACTUALLY HAD YOUR REVIEW MEETING
 * Where: PHYSICAL LOCATION AND/OR ONLINE

## Process - Reflection

(Optional) Short introduction

#### Decisions that turned out well

List process-related (i.e. team organization) decisions that, in retrospect, turned out to be successful.


 * 2 - 4 decisions.
 * Ordered from most to least important.
 * Explain why (i.e. give a supporting argument) you consider a decision to be successful.
 * Feel free to refer/link to process artifact(s).

 * Placing more focus on the core features has really helped us bring forward the MVP. By more clearly defining which features we need, and which are optional we were able to spend more time on the tasks that really mattered.
 * Having predefined roles for each member of the group turned out to be quite useful. Assigning different areas of development to different people allowed us to really master or at least almost fully develop each piece of the project. Subdividing the project in this way really made it feal less daunting and allowed for quicker deployment (i.e. merges to master).
 

#### Decisions that did not turn out as well as we hoped

List process-related (i.e. team organization) decisions that, in retrospect, were not as successful as you thought they would be.

 * 2 - 4 decisions.
 * Ordered from most to least important.
 * Feel free to refer/link to process artifact(s).
 
 * Using the project board for bugs didn't help as much as we first thought it would. We usually ended up still telling each other over Messenger when we had bugs that needed to be dealt with rather than creating an issue and placing it in the project board. 
 * Not prescheduling meetings also proved to be something we could have worked on. We had different times where we usually met up online (using Discord) but we never set an exact time to be used every week which resulted in a much less organized meeting.


#### Planned changes

List any process-related changes you are planning to make (if there are any)

 * Ordered from most to least important.
 * Explain why you are making a change.


## Product - Review

#### Goals and/or tasks that were met/completed:

 * From most to least important.
 * Refer/link to artifact(s) that show that a goal/task was met/completed.
 * If a goal/task was not part of the original iteration plan, please mention it.

 * (Goal) Setup Suggestions Server and deploy to Azure Cloud
   * server is now operational with /suggestions/word and /rhymes/word endpoints
   * http://csc301-team-11-lyrics.azurewebsites.net/rhymes/swim
   * http://csc301-team-11-lyrics.azurewebsites.net/suggestions/swim

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
	  * EditTexts were used to create editable chords
	  * A Verse class was created making the moving around of verses and the processes related to verses much simpler (e.g. numbering verses, changing the layout of the verses, etc.)
	  * Allowed for toggling between editing verse lyrics and chords so that the two don't get in each others way
  	* Implemented dynamic chords so that as lyrics were edited, the chords stayed in their relative position above specific words in the lyrics

#### Goals and/or tasks that were planned but not met/completed:


 * (Goal) Clicking on suggestion places it in lyrics
	  * Time constraint, was unable to complete it.

## Meeting Highlights

Going into the next iteration, our main insights are:

 * 2 - 4 items
 * Short (no more than one short paragraph per item)
 * High-level concepts that should guide your work for the next iteration.
 * These concepts should help you decide on where to focus your efforts.
 * Can be related to product and/or process.
