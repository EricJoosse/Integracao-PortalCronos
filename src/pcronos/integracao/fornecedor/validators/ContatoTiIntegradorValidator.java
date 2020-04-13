package pcronos.integracao.fornecedor.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import pcronos.integracao.fornecedor.annotations.ValidContatoTiIntegrador;
import pcronos.integracao.fornecedor.entidades.ContatoTiIntegrador;

public class ContatoTiIntegradorValidator implements ConstraintValidator<ValidContatoTiIntegrador, ContatoTiIntegrador> {

    @Override
    public void initialize(final ValidContatoTiIntegrador constraintAnnotation) {

    }

    @Override
    public boolean isValid(final ContatoTiIntegrador value,
                             final ConstraintValidatorContext context) {
    	return false;
    }
}