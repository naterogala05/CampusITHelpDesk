Campus IT Help Desk
CMP_SC / INFOTC 3330 - Group Assignment 2

IDE used: Eclipse IDE for Java Developers

Group members: Nate Rogala and Shane Singh
github repository: 

How to run:
Open src/helpdesk/Main.java.
Right-click → Run As → Java Application.

Design choices:
- Requests can only be resolved once.
- Each request can only be assigned to one technician.
- Duplicate assignments are rejected.
- Password resets award basePoints.
- Device setups award basePoints * deviceCount.
- Network issues award basePoints + affectedUsers.
- Technicians are equal when their names match exactly.

Expected demo results:
Alex: 35 points
Jordan: 37 points
Sam: 0 points
Total: 72 points

Running output screenshot:
screenshots/narcqp_HelpDesk_Output