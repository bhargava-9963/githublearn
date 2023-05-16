class doingsomething {
    public static void main(String[] args){
        String bits="00000010100101000001111010011100";
        char[] k=bits.toCharArray();
        double sum=0;
        int j=0;
        for(int i=bits.length()-1;i>=0;i--){
            if(k[i]=='1'){
                sum=sum+Math.pow(2,j);
            }
            j++;
        }
        System.out.println(sum);
    }
}
