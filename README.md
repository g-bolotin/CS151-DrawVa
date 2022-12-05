# DrawVa
### Team 2; Galit Bolotin, Bryan Dang, and Anubhav Sawhney

| Team Member | Proposal Contribution |
| ------- | ------- |
| Galit Bolotin | Created the timing diagram and sequence diagrams for the project.<br /><br />The timing diagram demonstrates the steps our program takes to function and estimated time these steps should take.<br /><br />The sequence diagrams show how a user would interact with each feature in our program and how the program would react to user interaction. |
| Bryan Dang| Contributed in formulating the proposal:<br />Mission Statement, Existing Programs, Working Environment, Usage, High Level Description, Operations, and References<br /><br />Created Class Diagram: This is the class diagram for the project DrawVa. It includes the Model, View, Controller, Observer, and Driver class Canvas attributes and methods.<br /><br />Created Use Case Chart: It is to show different type of operations a user might expect from a drawing application |
| Anubhav Sawhney| |

| Team Member | Presentation Contribution |
| ------- | ------- |
| Galit Bolotin | Presented the tools necessary for creating this project (Java, Java Swing) and existing programs similar to our project (MS Paint, Paint.NET, among numerous other painting apps).<br /><br />Demonstrated the brush thickness menu, save, restore, and download functions in the program. Explained the code behind each feature and how it was integrated into the MVC pattern. |
| Bryan Dang| Gave a quick introduction of the project, as well as an overview.<br /><br />Presented which Design Patterns were being used as well as how they were used<br /><br />Demonstrated how to run the application and went over a couple of features<br />Draw Mode allows user to draw.<br />Select Mode allows user to select what they have drawn and allows them to either delete or edit the drawn object<br />Edit Mode includes translate (move object), rotate, scale, and shear<br />Color Option for both stroke and fill color<br />Shapes to be drawn such as line, rectangle and ellipse<br /><br />Answered Q&A whenever applicable |
| Anubhav Sawhney| |

| Team Member | Code Contribution |
| ------- | ------- |
| Galit Bolotin | Created a slider for brush thickness which later went unused and was replaced by a dropdown menu.<br /><br />Created an experimental branch where I tried to fix gaps in freeform lines when drawing quickly. I suggested a collection of points with lines drawn between as a potential fix, from which proper freeform drawing was derived by Bryan.<br /><br />Fixed an issue with freeform brushstrokes being rendered as squares/pixel-like, making them smooth and rounded like existing drawing programs.<br /><br />Programmed the download functionality, allowing users to save their drawing as a PNG, JPG, or GIF to a location of their choice (Downloads folder by default). This includes visuals for the save dialog, backend file creation and saving, and a method for converting the canvas to an image.|
| Bryan Dang| Took the initial experimental code for the group and rewrote the program to follow an MVC Pattern<br /><br />Implemented Observer Pattern into project to notify changes in the model<br /><br />Created a structure using a custom CanvasShape class for object drawn by user<br /><br />Implemented a select mode<br /><br />Had to create options to edit/delete selected objects This was managed in the controller<br /><br />Implemented a draw mode<br /><br />Took Galit’s idea for freeform points and successfully created feature<br /><br />Implemented other drawing features such as line (Straight Line), rectangle, and ellipse.<br /><br />Replaced original Color code which was originally buttons attach to actionlisteners to change the color of brush (VERY BAD and redundant). Replaced it with JColorChooser a less redundant and more useful way to implement different colors<br /><br />Added Format button to make it easier for user to control strokes and colors<br /><br />Add images to show which button is to select (cursor) and draw (paintbrush) |
| Anubhav Sawhney| |

### Issue:
#### We wanted to create a free and simple to use drawing app which works on all computers that can run Java using components from Object Oriented Design

### Existing Programs (References): 
#### MS Paint. Softonic. (n.d.). Retrieved December 4, 2022, from https://ms-paint.en.softonic.com/ 
#### Pinoy Tutorial tv. (2020). How To Use Ms Paint - Ms Paint | Tutorial. YouTube. Retrieved December 4, 2022, from https://www.youtube.com/watch?v=KxHFwvRzDBs. 
#### Paint.NET. Softonic. (n.d.). Retrieved December 4, 2022, from https://paint-net.en.softonic.com/ 
#### Brewster, R. (n.d.). Features. Paint.NET. Retrieved December 4, 2022, from https://www.getpaint.net/features.html 
#### Gpaint - GNU Project - Free Software Foundation (FSF). (2007, September 1). Retrieved December 4, 2022, from https://www.gnu.org/software/gpaint/ 
#### Koblas, D. (1994, April 20). XPaint. Retrieved December 4, 2022, from https://sf-xpaint.sourceforge.net/ 

### Assumptions / Operating Environments / Intended Usage
#### Usage: A functioning java program that imitates a simple raster graphics editor.
#### Operating Environments: Anything that can run Java.
#### Intended Usage: A drawing app can have many uses, from doodling, taking notes, drawing charts, brainstorming, etc. The users are those who want to use a straightforward drawing app to visualize their ideas, no matter which PC or laptop they own.

| Diagram | Summary |
| ------- | ------- |
| [DrawVa_Class_Diagram.png](https://github.com/g-bolotin/CS151-DrawVa/blob/main/diagrams/DrawVa_Class_Diagram.png) | This is the class diagram for the project DrawVa. It includes the Model, View, Controller, Observer, and Driver class Canvas attributes and methods. |
| [DrawVa_Use_Case_Chart.png](https://github.com/g-bolotin/CS151-DrawVa/blob/main/diagrams/DrawVa_Use_Case_Chart.png) | This chart is to show different use cases the user may go through. |
| [DrawVa-Sequence-Diagrams.png](https://github.com/g-bolotin/CS151-DrawVa/blob/main/diagrams/DrawVa-Sequence-Diagrams.png) | A set of sequence diagrams for DrawVa. Includes a sequence diagram for tool settings, tool selection, and using the GUI.|
| [DrawVa-Timing-Diagram.png](https://github.com/g-bolotin/CS151-DrawVa/blob/main/diagrams/DrawVa-Timing-Diagram.png) | An estimated timing diagram for DrawVa. The program has 4 main steps: Listening for mouse actions, reacting to mouse actions, updating the GUI, and drawing on the canvas.|
| [DrawVa_StateDiagram.png](https://github.com/g-bolotin/CS151-DrawVa/blob/main/diagrams/DrawVa_Use_Case_Chart.png) | This chart is to show different states the user may go through and the behavior of the system. |

### Functionality:
#### A lot of devices can run Java, so creating DrawVa entirely in Java ensures that many devices can run it without issues.
#### Drawing: A freehand drawing tool, the shape creation tools, and a variety of color options allow users to visualize their ideas however they want.
#### Saving: Users can download their work in common image formats (JPG, PNG, GIF) for easy sharing.
#### Lightweight: The simplicity of DrawVa means that it takes very little memory to run and store.


### Operations:
#### File Operations
* New File: Clears Canvas
* Save File: Saves Canvas
* Download File: Downloads Canvas into computer's Download Folder
* Restore File: Allows User to reuse a previous canvas
* Exit: Exits Program
#### Edit Operations
* Select Mode
* Draw Mode
* Delete Shape
* Transform Shape
* Erase
#### Shape Dropdown Menu
* Freeform Line
* Straight Line
* Rectangle
* Ellipse
#### Stroke Thickness
* 1-10 px
#### Color Operations
* Fill Color
* Stroke Color
### Solution: 
#### Create a drawing app using only Java, GUIs, MVC Pattern, Observer Pattern, Java Swing
### Steps to run Program using DrawVa.jar provided(Note: Images was not used in jar file. Eclipse IDE does not give option to, therefore Select and Draw buttons do not look right):
| Steps | Details |
| ------- | ------- |
| 1 | Download DrawVa.jar |
| 2 | Open DrawVa.jar |
| 3 | Start drawing and get creative|

### Steps to run Program using Java IDE such as Eclipse
| Steps | Details |
| ------- | ------- |
| 1 | Open Elipse IDE |
| 2 | Create a new Java Project |
| 3 | Download all Classes provide in code folder and place them into project src package |
| 4 | Download icons folder and place it into same project |
| 5 | Run DrawVaDriver.java |
| 6 | Start drawing and get creative|


### Snapshots of the running program:
![Screenshot (206)](https://user-images.githubusercontent.com/91082267/205748317-2f486cf7-53b7-4a1b-9471-cb26352bb870.png)
![Screenshot (218)](https://user-images.githubusercontent.com/91082267/205748339-052e7eda-f180-4462-a58e-9f3100cb7d9c.png)
![Screenshot (219)](https://user-images.githubusercontent.com/91082267/205748354-b8b6d997-e3d7-4a45-a8cd-130f23655f1f.png)
![Screenshot (220)](https://user-images.githubusercontent.com/91082267/205748911-5ca94925-218a-412d-bcff-31688ca8792b.png)
![Screenshot (221)](https://user-images.githubusercontent.com/91082267/205748924-6e49a18d-90a5-4eac-863e-8ceb278245ff.png)
![Screenshot (222)](https://user-images.githubusercontent.com/91082267/205748936-ac34e4e5-c710-4eca-8872-1198e6d8bf60.png)
![Screenshot (223)](https://user-images.githubusercontent.com/91082267/205748947-711be23b-244b-49bd-8b80-e54b5aa0c9f4.png)
![Screenshot (224)](https://user-images.githubusercontent.com/91082267/205749200-4d9def8c-ecf4-4e1f-a0e5-cec147eb70f4.png)
![Screenshot (225)](https://user-images.githubusercontent.com/91082267/205749209-4066d3c3-d68e-4cb6-abd5-b870c2026646.png)
