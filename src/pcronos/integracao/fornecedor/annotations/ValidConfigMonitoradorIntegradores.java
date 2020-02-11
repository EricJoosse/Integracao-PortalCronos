package pcronos.integracao.fornecedor.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

import pcronos.integracao.fornecedor.entidades.ConfigMonitoradorIntegradores;
import pcronos.integracao.fornecedor.validators.ConfigMonitoradorIntegradoresValidator;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ConfigMonitoradorIntegradoresValidator.class)
public @interface ValidConfigMonitoradorIntegradores {
 String message() default "Invalid customer (due to edge case)";
 Class<?>[] groups() default { };
 Class<? extends Payload>[] payload() default { };
}

