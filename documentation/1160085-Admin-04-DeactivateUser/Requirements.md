## ADMIN - 04 - Deactivate User ##

### Short format ###

Admin logs in. The system shows the main menu. Admin chooses "Deactivate User". The system shows available users to deactivate. Admin chooses a user. The system shows available causes for deactivation. Admin chooses a cause for deactivation. The system shows input comment prompt. Admin inputs comment for deactivation. System notifies success.

### Complete format ###

### Main actor ###
* User with Administrator role (Admin)

### Partes interessadas e seus interesses ###
* Utilizador não registado: Pretende registar-se de modo a poder aceder a funcionalidades exclusivas a um utilizador registado.
* Centro de Eventos: Pretende obter um registo dos utilizadores da sua aplicação.

### Pré-condições ###
* User to deactivate must be active in the system.

### Pós-condições ###
* User is inactive. A cause and optional comment is inserted.

### UC basic flux ###
1.  Admin logs in.
2.  The system shows backOffice admin main menu.
3.  Admin chooses "Deactivate User".
4.  The system shows available users to deactivate.
5.  Admin chooses a user.
6.  The system shows available causes for deactivation.
7.  Admin chooses a cause for deactivation.
8.  The system shows input comment prompt.
9.  Admin inputs comment for deactivation.
10. System notifies success.

### UC alternative flux ###
*a. Admin exits aplication.

    2. UC ends.

4a. No user is eligible to be deactivated.

    1. The system notifies problem and returns to main menu.
    2. UC ends.

4b. Admin chooses to exit.

    1. The system returns to the main menu.
    2. UC ends.
    
7. Admin chooses to exit.

    1. The system returns to the main menu.
    2. UC ends.

9. Admin inputs no comment (presses ENTER to skip).

    1. The system changes the input comment to null.

### Special requirements ###
*

### List of variations in tecnology and data
* 

### Frequency of occurrence ###
*

### Questions ###

* Should the list of causes be persisted?