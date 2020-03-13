package pcronos.integracao.fornecedor.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pcronos.integracao.fornecedor.annotations.ValidConfigInstaladorIntegrador;
import pcronos.integracao.fornecedor.entidades.ConfigInstaladorIntegrador;

public class ConfigInstaladorIntegradorValidator implements ConstraintValidator<ValidConfigInstaladorIntegrador, ConfigInstaladorIntegrador> {

    @Override
    public void initialize(final ValidConfigInstaladorIntegrador constraintAnnotation) {

    }

    @Override
    public boolean isValid(final ConfigInstaladorIntegrador value,
                             final ConstraintValidatorContext context) {
    	return false;
    }
}