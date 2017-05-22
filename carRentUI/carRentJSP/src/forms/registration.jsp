<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div id="user-modal" class="modal bottom-sheet">
	<div class="modal-content">
		<p class="primary-text cabinet-title">
			<i><s:message code="reg.title" /></i>
		</p>
		<div class="row">
			<div class="col s6">
				<div class="input-field">
					<i class="material-icons prefix">label_outline</i> <input
						tabindex="1" type="text" id="reg-surname" /> <label
						for="reg-surname"><s:message code="reg.label.surname" /></label>
				</div>
			</div>
			<div class="col s6">
				<div class="input-field">
					<i class="material-icons prefix">perm_identity</i> <input
						tabindex="4" type="text" id="reg-username" /> <label
						for="reg-username"><s:message code="reg.label.username" /></label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s6">
				<div class="input-field">
					<i class="material-icons prefix">label_outline</i> <input
						tabindex="2" type="text" id="reg-name" /> <label for="reg-name"><s:message code="reg.label.name" /></label>
				</div>
			</div>
			<div class="col s3">
				<div class="input-field">
					<i class="material-icons prefix">vpn_key</i> <input tabindex="5"
						type="password" id="reg-password" /> <label for="reg-password"><s:message code="reg.label.password" /></label>
				</div>
			</div>
			<div class="col s3">
				<div class="input-field">
					<i class="material-icons prefix">vpn_key</i> <input tabindex="6"
						type="password" id="reg-repeat-password" /> <label
						for="reg-repeat-password"><s:message code="reg.label.password.repeat" /></label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s6">
				<div class="input-field">
					<i class="material-icons prefix">label_outline</i> <input
						tabindex="3" type="text" id="reg-patronymic" /> <label
						for="reg-patronymic"><s:message code="reg.label.patronymic" /></label>
				</div>
			</div>
			<div class="col s3">
				<div class="input-field">
					<i class="material-icons prefix">email</i> <input tabindex="7"
						type="email" id="reg-email" /> <label for="reg-email"><s:message code="reg.label.email" /></label>
				</div>
			</div>
			<div class="col s3">
				<div class="input-field">
					<i class="material-icons prefix">call</i> <input tabindex="8"
						type="text" id="reg-contact-number" /> <label
						for="reg-contact-number"><s:message code="reg.label.number" /></label>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<a onclick="registerUser()" id="reg-confirmation"
			class="waves-effect waves-light btn-large green accent-3"> <i
			class="primary-text cabinet-title"><s:message code="reg.button.submit" /></i>
		</a>
	</div>
</div>
</body>