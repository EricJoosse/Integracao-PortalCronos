package pcronos.integracao.fornecedor.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pcronos.integracao.fornecedor.annotations.ValidConfigMonitoradorIntegradores;
import pcronos.integracao.fornecedor.entidades.ConfigMonitoradorIntegradores;

//Validator:
public class ConfigMonitoradorIntegradoresValidator implements ConstraintValidator<ValidConfigMonitoradorIntegradores, ConfigMonitoradorIntegradores> {

    @Override
    public void initialize(final ValidConfigMonitoradorIntegradores constraintAnnotation) {

    }

    @Override
    public boolean isValid(final ConfigMonitoradorIntegradores value,
                             final ConstraintValidatorContext context) {
    	if (value.ApelidoContatoTIsecundario.equals(value.ApelidoContatoTI))
    		return false;
    	else
            return true;
    }
}