package pcronos.integracao.fornecedor.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pcronos.integracao.fornecedor.annotations.ValidConfigInstaladorIntegradorNuvem;
import pcronos.integracao.fornecedor.entidades.ConfigInstaladorIntegradorNuvem;

public class ConfigInstaladorIntegradorNuvemValidator implements ConstraintValidator<ValidConfigInstaladorIntegradorNuvem, ConfigInstaladorIntegradorNuvem> {

    @Override
    public void initialize(final ValidConfigInstaladorIntegradorNuvem constraintAnnotation) {

    }

    @Override
    public boolean isValid(final ConfigInstaladorIntegradorNuvem value,
                             final ConstraintValidatorContext context) {
    	return true;
    }
}