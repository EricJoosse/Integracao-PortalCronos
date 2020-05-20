package pcronos.integracao.fornecedor.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pcronos.integracao.fornecedor.annotations.ValidContatoTiIntegradorNuvem;
import pcronos.integracao.fornecedor.entidades.ContatoTiIntegradorNuvem;

public class ContatoTiIntegradorNuvemValidator implements ConstraintValidator<ValidContatoTiIntegradorNuvem, ContatoTiIntegradorNuvem> {

    @Override
    public void initialize(final ValidContatoTiIntegradorNuvem constraintAnnotation) {

    }

    @Override
    public boolean isValid(final ContatoTiIntegradorNuvem value,
                             final ConstraintValidatorContext context) {
    	return true;
    }
}