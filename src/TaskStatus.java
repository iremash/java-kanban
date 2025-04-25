enum TaskStatus {
    NEW,
    IN_PROGRESS,
    DONE;

    @Override
    public void toString(){
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}