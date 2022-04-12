class User{
    constructor(email, username, password){
        this.email = email;
        this.username = username;
        this.password = password;
        this.location = null;
    }

    // getters
    getEmail(){return this.email;}
    getUsername(){return this.username;}
    getPassword(){return this.password;}
    getLocation(){return this.location;}

    // find user's location
    findLocation(){
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition( (pos) => {
                this.location = {"lat": pos.coords.latitude, "long": pos.coords.longitude};
                //this.location = pos.coords;
            });
        } else{
            alert('Location API not supported!');
          }
        
        return this;
    }

    // method which returns driver's loc. 
    getLocation(){return this.location;}

    // email validator
    validateEmail(){
        return String(this.email).toLowerCase().match(
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        );
    };
  
    // username allowed to be from 6-20 chars long, contain lower, upper, digits and '_'
    validateUsername(){
        return String(this.username)
            .match(/^[a-zA-Z0-9_]{6,20}[a-zA-Z]+[0-9]*$/);
    };

    // At least: 8 chars, 1 Upper, 1 lower, 1 special char
    validatePass(){
        return String(this.password)
            .match(/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/);
    };
    
}

class Driver extends User{
    constructor(email, username, password, pTime){
        super(email, username, password);
        this.pTime = pTime;
    }
    
    // method to refresh parking time
    refreshTime(t){this.pTime = t;}
}

class Valet extends User{
    constructor(email, username, password, name, spots){
        super(email, username, password);
        this.name = name;
        this.spots = spots;
    }
}


// run examples
const driver = new Driver('gkont@gmail.com', 'gkontogiannhs', 'ceidHell1@', 50);

console.log(driver.getEmail());
if(driver.validatePass())
    console.log('Passwrod is fine');
else
    console.log('Password is not fine');

driver.refreshTime(10);
console.log(driver.pTime);


const valet = new Valet('valet@gmail.com', 'valetomou', 'BestValet1@', 'Auto Parking Patras', 17);
console.log(valet.findLocation().getLocation());