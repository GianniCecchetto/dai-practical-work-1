package ch.heigvd.dai.loadingbar;

public class LoadingBar {

    private String title;
    private int nbBlock;
    private int oldPourcentage = 0;
    private boolean finished = false;

    public void initLoadingBar(String title,int nbBlock){
       this.title = title;
       if(nbBlock < 0 || nbBlock > 100)
           nbBlock = 100;
       this.nbBlock = nbBlock;
       this.finished = false;
        System.out.print(title + " |"+ "▒".repeat(nbBlock)+"|\r");
    }

    private String getLoadingBar(int progressBlocks,int pourcentage){
        return  title + " |"
                + "█".repeat(progressBlocks)
                + "▒".repeat(nbBlock - progressBlocks)
                + "| " + pourcentage + "%";
    }

    public void updateLoadingBar(int percentage){

        if(oldPourcentage == percentage)
            return;

        oldPourcentage = percentage;
        int progressBlocks = percentage / (100/nbBlock);
        if(nbBlock - progressBlocks  < 0 && !finished){
            finished = true;
            System.out.print("\r"+title + " |"+ "█".repeat(nbBlock)+"| 100%\r");
            return;
        }
        if(!finished){
            String loadingBar = getLoadingBar(progressBlocks,percentage);
            System.out.print("\r" + loadingBar);
        }


    }

    public void resetBar(){
        finished = false;
    }

}
