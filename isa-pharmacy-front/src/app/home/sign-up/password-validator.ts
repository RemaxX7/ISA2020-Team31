import { AbstractControl } from '@angular/forms';

export class PasswordValidator {

    static passwordMatchValidator(control: AbstractControl) {
        const password: string = control.get('password').value;
        const confirmPassword: string = control.get('passwordControl').value;
        if (password !== confirmPassword) {
          control.get('passwordControl').setErrors({ NoPassswordMatch: true });
        }
    }
}