<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<div id="user-modal" class="modal bottom-sheet">
	<div class="modal-content">
		<p class="primary-text cabinet-title">
			<i><s:message code="password.change.title" /></i>
		</p>
		<div class="row">
			<div class="col s8">
				<div class="input-field">
					<i class="material-icons prefix">vpn_key</i>
					<input tabindex="1" type="password" id="fp-old-password" />
					<label for="fp-old-password"><s:message code="password.change.label.old" /></label>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col s8">
				<div class="input-field">
					<i class="material-icons prefix">vpn_key</i>
					<input tabindex="2" type="password" id="fp-new-password" />
					<label for="fp-new-password"><s:message code="password.change.label.new" /></label>
				</div>
			</div>
			<div class="col s4">
				<a onclick="changePassword()" id="reg-confirmation"
					class="waves-effect waves-light btn-large green accent-3"> <i
					class="primary-text cabinet-title"><s:message code="password.change.button.submit" /></i></a>
			</div>
		</div>
		<div class="row">
			<div class="col s8">
				<div class="input-field">
					<i class="material-icons prefix">vpn_key</i>
					<input tabindex="3" type="password" id="fp-repeat" />
					<label for="fp-repeat"><s:message code="password.change.label.repeat" /></label>
				</div>
			</div>
		</div>
	</div>
</div>