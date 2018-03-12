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
	* Those unfamiliar with the Git workflow or not understanding how branches were organized might do some messy merges that resulted in local files being deleted, wasting time and resources in recovering the information.
	* Deleting the work of other's by "resolving" conflicts on their own without properly reviewing the content.
	* Committing directly to master with an older version of the codebase and reverting commits made by other contributors
	* In the end, things smoothed out more, but there continue to be rough patches that will hopefully become a smooth and well-oiled machine going into interation 03
 * As a follow-up on the previous point, our lack of strict conventions and guidelines going into iteration 02 for how we conduct our pull-requests and merges have left portions of our commit logs to be messy and disheveled. 
	* Instead of making pull-requests, there have been instances where commits are made directly to master and may at times have volatile consequences to our codebase and require a retcon. These cases can be avoided by submitting pull-requests and requiring a review for the merge before committing it to the master branch. 
 * Our way of conducting meetings has been quite loose, as oftentimes meetings are re-scheduled due to shifting availabilities of members, and we have used Discord far less than resources like Messenger in our communications. The end result is meetings where not everyone talks, or is able to talk, or meetings that are held on short notice.
 * Deciding not to have naming conventions for our commits and merges can make reviewing the commit history a tedious process
	* Names like "Whoops" for commits such as a204d0943a010bfbe99ddaec26d5347356315443 are not very informative
	* Using generic merge titles (even if the descriptions were more verbose) such as "Merge pull request #__ from csc301-winter-2018/Audio-Recording" are not very insightful. They make sense at the time, in context, when we're talking on Messenger, but upon review they hold no information at first glance.


#### Planned changes

List any process-related changes you are planning to make (if there are any)

 * Establish firm conventions for how we conduct pull-requests, merges, and commits
	* For example: NEVER directly commit anything to the codebase. All code should be merged via pull-request
	* Also any conflicts made during a pull request, if it interferes with another member's submitted code, should ALWAYS be reviewed by one or more parties before merging
	* One should always try to make merges with purpose or valuable content. 
 * A naming convention for pull-requests and commits should most likely be implemented, such that the messages convey some information as to what was changed or added during the merge.

## Product - Review

#### Goals and/or tasks that were met/completed:

 * From most to least important.
 * Refer/link to artifact(s) that show that a goal/task was met/completed.
 * If a goal/task was not part of the original iteration plan, please mention it.

#### Goals and/or tasks that were planned but not met/completed:

 * From most to least important.
 * For each goal/task, explain why it was not met/completed.      
   e.g. Did you change your mind, or did you just not get to it yet?

## Meeting Highlights

Going into the next iteration, our main insights are:

 * 2 - 4 items
 * Short (no more than one short paragraph per item)
 * High-level concepts that should guide your work for the next iteration.
 * These concepts should help you decide on where to focus your efforts.
 * Can be related to product and/or process.
