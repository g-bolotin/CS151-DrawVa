# DrawVa

### Team 2

### Group members: 
#### Galit Bolotin, Bryan Dang, and Anubhav Sawhney

### Mission statement: 
#### Free and simple to use drawing app which works on all computers (I.e. Mac, linux, windows, chromeOS, high end computer, low end computer, etc.)

### Existing Programs (References): 
#### MS Paint, GNU Paint, Paint.NET

### Working Environment: 
#### Java Eclipse IDE

### Usage: 
#### A functioning java program using GUI that imitates a simple raster graphics editor.

### High Level Descriptions: This program would follow a MVC pattern.

    Draw: A draw class (Model)
    This class will initializes all GUI (buttons), keeps track and sets all actions performed

    Canvas: A canvas class which extends from JComponent (View)
    This class will be in charge of initializing the canvas board that a user can draw on, shows all GUI involving with what the user sees at the surface (pencil, eraser, colors, shapes, etc), and keep track of all mouselistener components

    Controller: A controller class
    This class keeps track of all GUI involving with what the user sees at the surface (pencil, eraser, colors, shapes, etc)

    Driver: A driver class to run the program

    
### Intended Functionality: 
#### A drawing app can have many uses, from doodling, taking notes, drawing charts, brainstorming, etc. The users are those who want to use a straightforward drawing app to visualize their ideas, no matter which PC or laptop they own.

### Operations:

    Draw: The user will be able to freely draw all over the generated canvas
    Draw shapes: Simple shapes will be already implemented to allow user to draw certain shapes
    Clear: Allow user to fill with whitespace
    Erase: Allows user to erase their drawings
    Choose Colors: Allows user to choose from a range of colors
    Choose line thickness: Allows user to choose the thickness of their lines

### References:

    https://en.wikipedia.org/wiki/Raster_graphics_editor
    https://www.tutorialspoint.com/design_pattern/mvc_pattern.htm
    https://www.javatpoint.com/java-swing
    https://docs.oracle.com/javase/7/docs/api/javax/swing/JComponent.html
    https://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseListener.html
    https://docs.oracle.com/javase/7/docs/api/java/awt/event/ActionListener.html
