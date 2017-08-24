int rectX, rectY;
int rectSize = 90;

color rectColor;
color rectHighlight;
color currentColor;
color baseColor;
boolean rectOver = false;

void setup(){
  size(1024,500);
  rectColor = color(102);
  rectHighlight = color(51);
  baseColor = color(0);
  currentColor = baseColor;
  rectX = width/2 - rectSize-10;
  rectY = height/2-rectSize/2;
  ellipseMode(CENTER);
}

void draw(){
  update(mouseX, mouseY);
  background(currentColor);
  if (rectOver){
    fill(rectHighlight);
  }else{
    fill(rectColor);
  }
  stroke(255);
  rect(rectX, rectY, rectSize, rectSize);

}

void update(int x, int y){
  if(overRect(rectX, rectY, rectSize, rectSize)){
    rectOver = true;
  }else{
    rectOver = false;
  }
}

void mousePressed(){
  
}

boolean overRect(int x , int y, int width, int height){
  if (mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height){
    return true;
  }else{
    return false;
  }
}
