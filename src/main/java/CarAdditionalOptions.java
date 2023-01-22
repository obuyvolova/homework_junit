
public class CarAdditionalOptions {


    //Добавить комплект зимней резины
    public int addWinterTires (int numberOfTires, int costPerTire) throws CarZeroTiresExceptions {
        if (numberOfTires == 0) {
            throw new CarZeroTiresExceptions("Введенное количество шин равняется 0!");
        } else {
            return numberOfTires * costPerTire;
        }
    }


    //Поставить машину на учет
    public String putTheCarOnRegister (String number, String region) {
        return number + " " + region;
    }

}
